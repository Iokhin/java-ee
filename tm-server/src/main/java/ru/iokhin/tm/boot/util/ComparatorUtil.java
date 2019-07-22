package ru.iokhin.tm.boot.util;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.boot.model.entity.Project;
import ru.iokhin.tm.boot.model.entity.Task;

import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ComparatorUtil {
    public static Comparator<Project> getProjectComparator(@NotNull final String comparator) {
        Comparator<Project> dateStartComparator = (o1, o2) -> {
            Date date1 = o1.getDateStart();
            Date date2 = o2.getDateStart();
            if (date1 == null) date1 = new Date(Long.MAX_VALUE);
            if (date2 == null) date2 = new Date(Long.MAX_VALUE);
            return date1.compareTo(date2);
        };

        Comparator<Project> dateEndComparator = (o1, o2) -> {
            Date date1 = o1.getDateEnd();
            Date date2 = o2.getDateEnd();
            if (date1 == null) date1 = new Date(Long.MAX_VALUE);
            if (date2 == null) date2 = new Date(Long.MAX_VALUE);
            return date1.compareTo(date2);
        };

        Comparator<Project> statusComparator = Comparator.comparing(Project::getStatus);

        Map<String, Comparator<Project>> comparatorMap = new HashMap<>(0);
        comparatorMap.put("dateStart", dateStartComparator);
        comparatorMap.put("dateEnd", dateEndComparator);
        comparatorMap.put("status", statusComparator);
        return comparatorMap.get(comparator);
    }

    public static Comparator<Task> getTaskComparator(@NotNull final String comparator) {
        Comparator<Task> dateStartComparator = (o1, o2) -> {
            Date date1 = o1.getDateStart();
            Date date2 = o2.getDateStart();
            if (date1 == null) date1 = new Date(Long.MAX_VALUE);
            if (date2 == null) date2 = new Date(Long.MAX_VALUE);
            return date1.compareTo(date2);
        };

        Comparator<Task> dateEndComparator = (o1, o2) -> {
            Date date1 = o1.getDateEnd();
            Date date2 = o2.getDateEnd();
            if (date1 == null) date1 = new Date(Long.MAX_VALUE);
            if (date2 == null) date2 = new Date(Long.MAX_VALUE);
            return date1.compareTo(date2);
        };

        Comparator<Task> statusComparator = Comparator.comparing(Task::getStatus);

        Map<String, Comparator<Task>> comparatorMap = new HashMap<>(0);
        comparatorMap.put("dateStart", dateStartComparator);
        comparatorMap.put("dateEnd", dateEndComparator);
        comparatorMap.put("status", statusComparator);
        return comparatorMap.get(comparator);
    }
}
