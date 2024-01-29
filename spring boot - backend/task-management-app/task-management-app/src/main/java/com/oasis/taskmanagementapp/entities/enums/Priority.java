package com.oasis.taskmanagementapp.entities.enums;

public enum Priority {
//    CRITICAL, HIGH, MEDIUM, LOW,

    CRITICAL("Critical"),
    HIGH("High"),

    MEDIUM("Medium"),

    LOW("Low");

    private final String priority;

    Priority(String priority) {
        this.priority = priority;
    }
}
