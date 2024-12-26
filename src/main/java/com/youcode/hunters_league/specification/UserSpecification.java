package com.youcode.hunters_league.specification;

import com.youcode.hunters_league.domain.AppUser;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class UserSpecification {

    public static Specification<AppUser> filterUser(String firstName, String lastName, String cin) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.isNotBlank(firstName)) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("firstName")),
                        likePattern(firstName.toLowerCase())
                ));
            }

            if (StringUtils.isNotBlank(lastName)) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("lastName")),
                        likePattern(lastName.toLowerCase())
                ));
            }

            if (StringUtils.isNotBlank(cin)) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("cin")),
                        likePattern(cin.toLowerCase())
                ));
            }

            if (predicates.isEmpty()) {
                return criteriaBuilder.conjunction();
            }

            // Combine all predicates with AND
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    private static String likePattern(String value) {
        return "%" + value + "%";
    }
}