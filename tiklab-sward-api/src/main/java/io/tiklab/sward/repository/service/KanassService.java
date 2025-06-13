package io.tiklab.sward.repository.service;

import io.tiklab.sward.support.model.kanassProject;

import java.util.List;

public interface KanassService {
    List<kanassProject> findAllProject();
}
