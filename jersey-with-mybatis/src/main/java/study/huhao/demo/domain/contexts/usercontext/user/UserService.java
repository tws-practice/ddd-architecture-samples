package study.huhao.demo.domain.contexts.usercontext.user;

import study.huhao.demo.domain.core.concepts.DomainService;

public class UserService implements DomainService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
