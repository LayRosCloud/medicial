package by.betrayal.personalservice.utils;

import java.util.List;

public record ResponseLimitPage<TEntity>(List<TEntity> items, Long totalCount) {
}
