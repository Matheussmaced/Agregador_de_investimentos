package tech.com.agregadordeinvestimentos.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.com.agregadordeinvestimentos.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
}
