package io.tiklab.sward.repository.service;

import io.tiklab.sward.support.model.Project;

import java.util.List;

public interface TeamWireService {
    List<Project> findAllProject();
}
