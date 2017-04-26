package com.example.student.everydayhero;

import java.util.List;

/**
 * Created by Anna on 28.03.2017.
 */

public interface IDataBaseHandler {
    void addObjective(Objective objective);
    Objective getObjective(int id);
    List<Objective> getAllObjectives();
    int getObjectivesCount();
    int updateObjective(Objective objective);
    void deleteObjective(Objective objective);
    void deleteAll();

}
