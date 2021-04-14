package cn.peilion.model.pojos.organization;

import lombok.Data;

import java.util.List;

/**
 * @author fpl11
 */
@Data
public class TreeItem {
    Integer id;
    String  name;
    Integer parentId;
    Integer level;
    List<TreeItem> children;
    String color;
    Integer size;
}
