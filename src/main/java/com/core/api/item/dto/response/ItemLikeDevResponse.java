package com.core.api.item.dto.response;

import com.core.api.item.entity.ItemLike;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemLikeDevResponse {
    private Long id;
    private Long itemId;
    private Long uid;

    public static ItemLikeDevResponse from(ItemLike itemLike) {
        return new ItemLikeDevResponse(
                itemLike.getId(),
                itemLike.getItemId(),
                itemLike.getUid()
        );
    }
}
