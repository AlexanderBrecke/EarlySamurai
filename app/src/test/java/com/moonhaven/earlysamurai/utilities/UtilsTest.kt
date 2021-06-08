package com.moonhaven.earlysamurai.utilities

import com.moonhaven.earlysamurai.database.IdeaObject
import com.moonhaven.earlysamurai.enums.Category
import com.moonhaven.earlysamurai.enums.IdeaStatus
import org.junit.Test

import org.junit.Assert.*

class UtilsTest {

    @Test
    fun getCategoriesString() {
        val categories1 = listOf(Category.Finance, Category.Medicine)
        val categories2 = listOf(Category.Finance)
        val categories3 = listOf(Category.Finance,Category.Medicine,Category.Environment)

        val result1 = Utils.getCategoriesString(categories1)
        val result2 = Utils.getCategoriesString(categories2)
        val result3 = Utils.getCategoriesString(categories3)

        assertEquals("Finance, Medicine", result1)
        assertEquals("Finance",result2)
        assertEquals("Finance, Medicine, Environment",result3)
    }

    @Test
    fun getStatusIdeasCount() {

        val idea1 = IdeaObject("asdf",Category.Environment,IdeaStatus.ForSale,"asdf")
        val idea2 = IdeaObject("asdf", Category.Environment, IdeaStatus.Sold, "asdf")

        val list1 = listOf(idea1,idea1,idea1,idea1)
        val list2 = listOf(idea1,idea1,idea1)
        val list3 = listOf(idea1,idea1,idea2,idea2,idea2)

        val result1 = Utils.getStatusIdeasCount(list1, IdeaStatus.ForSale)
        val result2 = Utils.getStatusIdeasCount(list2,IdeaStatus.Sold)
        val result3 = Utils.getStatusIdeasCount(list2,IdeaStatus.ForSale)
        val result4 = Utils.getStatusIdeasCount(list3,IdeaStatus.Sold)

        assertEquals(4, result1)
        assertEquals(0,result2)
        assertEquals(3,result3)
        assertEquals(3,result4)

    }
}