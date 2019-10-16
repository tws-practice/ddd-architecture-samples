package study.huhao.demo.domain.usercontext.user;

import study.huhao.demo.domain.core.concepts.AggregateRoot;

import java.util.UUID;

public class User implements AggregateRoot {
    private UUID id;
    private String name;

    User(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }

    public User(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
