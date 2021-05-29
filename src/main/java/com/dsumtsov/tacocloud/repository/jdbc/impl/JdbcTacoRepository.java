package com.dsumtsov.tacocloud.repository.jdbc.impl;

import com.dsumtsov.tacocloud.domain.Ingredient;
import com.dsumtsov.tacocloud.domain.Taco;
import com.dsumtsov.tacocloud.repository.jdbc.TacoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.asm.Type;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JdbcTacoRepository implements TacoRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Long save(Long orderId, int orderKey, Taco taco) {

        taco.setCreatedAt(new Date());
        PreparedStatementCreatorFactory pscf =
                new PreparedStatementCreatorFactory(
                        "insert into Taco "
                                + "(name, created_at, taco_order, taco_order_key) "
                                + "values (?, ?, ?, ?)",
                        Types.VARCHAR, Types.TIMESTAMP, Type.LONG, Type.LONG
                );
        pscf.setReturnGeneratedKeys(true);

        PreparedStatementCreator psc =
                pscf.newPreparedStatementCreator(
                        Arrays.asList(
                                taco.getName(),
                                taco.getCreatedAt(),
                                orderId,
                                orderKey));

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(psc, keyHolder);
        long tacoId = keyHolder.getKey().longValue();
        taco.setId(tacoId);

        saveTacoIngredients(tacoId, taco.getIngredients());

        return tacoId;
    }

    private void saveTacoIngredients(long tacoId, List<Ingredient> ingredients) {

        int key = 0;
        for (Ingredient ingredient : ingredients) {
            jdbcTemplate.update(
                    "insert into Taco_Ingredients (ingredient, taco, taco_key) "
                            + "values (?, ?, ?)",
                    ingredient.getId(), tacoId, key++);
        }
    }
}
