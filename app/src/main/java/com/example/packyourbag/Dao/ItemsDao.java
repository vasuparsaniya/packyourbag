package com.example.packyourbag.Dao;

import static androidx.room.OnConflictStrategy.REPLACE;

import android.content.Intent;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.packyourbag.Models.Items;

import java.util.List;

@Dao
public interface ItemsDao {

    @Insert(onConflict = REPLACE)
    void saveItem(Items items);

    @Query("select * from items where category=:category order by id asc")
    List<Items> getAll(String category);

    @Delete
    void delete(Items items);

    @Query("update items set checked=:checked where ID=:id")
    void checkUncheck(int id, boolean checked);

    @Query("select count(*) from items")
    Intent getItemsCount();

    @Query("delete from items where addedby=:addedBy")
    Integer deleteAllSystemItems(String addedBy);

    @Query("delete from items where category=:category")
    Integer deleteAllByCategory(String category);

    //those data enter by user is delete
    @Query("delete from items where category=:category and addedby=:addedBy")
    Integer deleteAllBycategoryAndAddedBy(String category,String addedBy);

    @Query("select * from items where checked=:checked order by id asc")
    List<Items> getAllSelected(Boolean checked);

}