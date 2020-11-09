package com.adongs.manager;

import com.adongs.JenkinsClient;
import com.adongs.api.BuildQueue;
import com.adongs.api.ViewInfo;
import com.adongs.bus.SynchronousDataTopic;
import com.adongs.bus.UpdateConfigTopic;
import com.adongs.bus.UpdateTreeTopic;
import com.adongs.config.AccountConfig;
import com.adongs.http.HttpReques;
import com.adongs.model.*;
import com.adongs.setting.PersistentConfig;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ComponentManager;
import com.intellij.util.messages.MessageBus;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author yudong
 * @version 1.0
 * @date 2020/11/6 10:33 上午
 * @modified By
 */
public class DataManager implements SynchronousDataTopic, UpdateConfigTopic {

    private static Map<String, JobNode> jobMap = new HashedMap();

    private static Map<String, FolderNode> viewMap = new HashedMap();

    private static Optional<ViewInfo> viewInfo = Optional.empty();

    private static Optional<BuildQueue> buildQueue = Optional.empty();

    public DataManager() {
        final MessageBus messageBus = ApplicationManager.getApplication().getMessageBus();
        messageBus.connect().subscribe(SynchronousDataTopic.CHANGE_ACTION_TOPIC,this);
        messageBus.connect().subscribe(UpdateConfigTopic.CHANGE_ACTION_TOPIC,this);
        final JenkinsClient jenkinsClient = JenkinsClientManager.get();
        if (jenkinsClient!=null){
            viewInfo = Optional.ofNullable(jenkinsClient.getView());
            buildQueue = Optional.ofNullable(jenkinsClient.getBuildQueue());
        }
    }

    @Override
    public void updateConfig(JenkinsClient jenkinsClient) {
        viewInfo = Optional.ofNullable(jenkinsClient.getView());
        buildQueue = Optional.ofNullable(jenkinsClient.getBuildQueue());
        synchronous();
        final MessageBus messageBus = ApplicationManager.getApplication().getMessageBus();
        final UpdateTreeTopic updateTreeTopic = messageBus.syncPublisher(UpdateTreeTopic.CHANGE_ACTION_TOPIC);
        updateTreeTopic.updateData();
    }



    @Override
    public void synchronous() {
        viewInfo.ifPresent(v->{
            final List<View> globalView = v.global();
            for (View view : globalView) {
                final FolderNode folderNode = viewMap.getOrDefault(view.getName(),new FolderNode(view.getName(),view.getJobs().size()+"个任务"));
                folderNode.removeAllChildren();
                final List<JobNode> jobNodeList = synchronousJob(view);
                jobNodeList.stream().forEach(folderNode::add);
            }
        });
        synchronousBuildQueue();
        synchronousExecutors();
    }


    @Override
    public void stateSynchronous() {
        synchronousBuildQueue();
        synchronousExecutors();
        final MessageBus messageBus = ApplicationManager.getApplication().getMessageBus();
        final UpdateTreeTopic updateTreeTopic = messageBus.syncPublisher(UpdateTreeTopic.CHANGE_ACTION_TOPIC);
        updateTreeTopic.refreshUI();
    }

    /**
     * 同步任务
     * @param view
     * @return
     */
   private List<JobNode> synchronousJob(View view){
       final List<Job> jobs = view.getJobs();
       List<JobNode> jobNodeList = new ArrayList<>();
       for (Job job : jobs) {
           final JobNode jobNode = jobMap.getOrDefault(job.getName(), new JobNode(job));
           if (jobMap.containsKey(job.getName())) {
               jobNode.setJob(job);
           }else{
               jobMap.put(job.getName(),jobNode);
           }
           jobNodeList.add(jobNode);
       }
    return jobNodeList;
   }

    /**
     * 同步构建队列
     */
  private void synchronousBuildQueue(){
      jobMap.values().stream().forEach(j->j.clearBuildQueue());
      buildQueue.ifPresent(b->{
          final List<QueueJob> queueJobs = b.buildQueue();
          if (!queueJobs.isEmpty()){
              for (QueueJob queueJob : queueJobs) {
                  final JobNode jobNode = jobMap.get(queueJob.getName());
                  if (jobNode!=null){
                      jobNode.setBuildQueue(queueJob);
                  }
              }
          }
      });
  }

    /**
     * 同步构建中
     */
  private void synchronousExecutors(){
      jobMap.values().stream().forEach(j->j.clearExecutors());
      buildQueue.ifPresent(b->{
          final List<QueueJob> executors = b.executors();
          if (!executors.isEmpty()) {
              for (QueueJob executor : executors) {
                  final JobNode jobNode = jobMap.get(executor.getName());
                  if (jobNode != null) {
                      jobNode.setExecutors(executor);
                  }
              }
          }
      });
  }



}
