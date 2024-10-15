package io.thoughtware.sward.recycle.service;

import io.thoughtware.sward.node.model.Node;
import io.thoughtware.sward.node.model.NodeQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface NodeRecycleService {
    void recycleNode(@NotNull @Valid Node node);

    void recoverRecycleNode(@NotNull @Valid Node node);

    List<Node> findRecycleNode(@NotNull @Valid NodeQuery nodeQuery);
}
