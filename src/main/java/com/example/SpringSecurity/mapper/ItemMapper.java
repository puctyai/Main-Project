package com.example.SpringSecurity.mapper;

import com.example.SpringSecurity.dto.ItemDTO;
import com.example.SpringSecurity.model.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    @Mapping(source = "name", target = "itemName")
    ItemDTO toItemDTO(Item item);

    @Mapping(source = "itemName", target = "name")
    Item toItem(ItemDTO itemDTO);
   List<ItemDTO> toListDTO(List<Item> items);
   List <Item> toListItem(List<ItemDTO> itemDTOS);
}
