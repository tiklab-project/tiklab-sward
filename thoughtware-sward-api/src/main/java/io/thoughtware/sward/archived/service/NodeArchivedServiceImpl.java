package io.thoughtware.sward.archived.service;

import io.thoughtware.sward.node.model.Node;
import io.thoughtware.sward.node.model.NodeQuery;
import io.thoughtware.sward.node.service.NodeService;
import io.thoughtware.user.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.thoughtware.eam.common.context.LoginContext;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NodeArchivedServiceImpl implements NodeArchivedService{

    @Autowired
    NodeService nodeService;

    @Override
    public void archivedNode(Node node) {
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = formater.format(new Date());
        node.setArchivedTime(format);

        String createUserId = LoginContext.getLoginId();
        User user = new User();
        user.setId(createUserId);
        node.setArchivedUser(user);

        String type = node.getType();

        nodeService.updateNode(node);
        if(type.equals("category")){
            List<Node> allChildrenNodeList = nodeService.findAllChildrenNodeList(node.getId());
            for (Node node1 : allChildrenNodeList) {
                node1.setArchivedTime(format);
                node1.setArchivedDesc(node.getArchivedDesc());
                node1.setArchivedUser(user);
                node1.setStatus("archived");
                nodeService.updateNode(node1);
            }
        }
    }

    @Override
    public void recoverArchivedNode(Node node) {
        node.setArchivedTime("nullstring");
        User user = new User();
        user.setId("nullstring");
        node.setArchivedUser(user);

        String type = node.getType();
        nodeService.updateNode(node);

        String treePath = node.getTreePath();
        if(!StringUtils.isEmpty(treePath) ){
            String[] ids = treePath.split(";");
            System.out.println(ids);
            NodeQuery nodeQuery = new NodeQuery();
            nodeQuery.setIds(ids);
            List<Node> nodeList = nodeService.findNodeList(nodeQuery);
            for (Node node1 : nodeList) {
                String status = node1.getStatus();
                if(status.equals("archived")){
                    node1.setArchivedUser(user);
                    node1.setStatus("nomal");
                    node1.setArchivedTime("nullstring");
                    nodeService.updateNode(node1);
                }
            }
        }
    }

//    @Override
//    public List<Node> findArchivedNode(NodeQuery nodeQuery) {
//
//        List<Node> nodeList = nodeService.findNodeList(nodeQuery);
//        List<Integer> deepList = nodeList.stream().map(node -> node.getDimension()).collect(Collectors.toList());
//        // 一般深度是[1, 2] 或者 [3, 4]
//        Integer[] deeps = deepList.toArray(new Integer[deepList.size()]);
//        Arrays.sort(deeps);
//        List<Integer> dimensionList = new ArrayList<Integer>(Arrays.asList(deeps));
//        Integer minDimensions = dimensionList.get(0);
//        List<Node> firstNodeList = nodeList.stream().filter(node -> node.getDimension().equals(minDimensions)).collect(Collectors.toList());
//        //删除已被使用的node
//        if(nodeList.size() > 0){
//            nodeList.removeAll(firstNodeList);
//            List<Node> nodes = setChildrenNode(nodeList, firstNodeList, dimensionList);
//            firstNodeList.addAll(nodes);
//        }
//
//        return firstNodeList;
//    }
//    List<Node> setChildrenNode(List<Node> nodeList, List<Node> firstNodeList, List<Integer> dimensionList){
//        List<Node> residueNodeList = new ArrayList<>();
//        if(nodeList.size() > 0){
//            for (Node firstLeavelNode : firstNodeList) {
//                List<Node> childrenList = nodeList.stream().filter(node -> node.getParent() != null &&
//                        node.getParent().getId().equals(firstLeavelNode.getId())).collect(Collectors.toList());
//                if(childrenList.size() > 0){
//                    for (Node node : childrenList) {
//                        node.setChildren(null);
//                    }
//                    nodeList.removeAll(childrenList);
//                    residueNodeList = nodeList;
//                    firstLeavelNode.setChildren(childrenList);
//                    if(nodeList.size() > 0){
//                        setChildrenNode(nodeList, childrenList, dimensionList);
//                    }
//                }else {
//                    firstLeavelNode.setChildren(null);
//                }
//            }
//        }
//        return residueNodeList;
//    }

    @Override
    public List<Node> findArchivedNode(NodeQuery nodeQuery) {
        List<Node> nodeList = nodeService.findNodeList(nodeQuery);
        return nodeList;
    }
}