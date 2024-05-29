package io.thoughtware.sward.archived.service;

import io.thoughtware.sward.category.model.WikiCategory;
import io.thoughtware.sward.node.model.Node;
import io.thoughtware.sward.node.model.NodeQuery;
import io.thoughtware.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface NodeArchivedService {
    void archivedNode(@NotNull @Valid Node node);

    void recoverArchivedNode(@NotNull @Valid Node node);

    List<Node> findArchivedNode(@NotNull @Valid NodeQuery nodeQuery);
}
