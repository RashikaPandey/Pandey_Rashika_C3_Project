import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Arrays;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RestaurantTest {
    Restaurant restaurant;

    @BeforeEach
    public void setUp() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = new Restaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);
        // Display restaurant details
        //restaurant.displayDetails();
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        Restaurant spyRestaurant = Mockito.spy(restaurant);
        LocalTime currentTime = LocalTime.parse("12:00:00");
        when(spyRestaurant.getCurrentTime()).thenReturn(currentTime);

        assertTrue(spyRestaurant.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        Restaurant spyRestaurant = Mockito.spy(restaurant);
        LocalTime currentTime = LocalTime.parse("23:00:00");
        when(spyRestaurant.getCurrentTime()).thenReturn(currentTime);

        assertFalse(spyRestaurant.isRestaurantOpen());
    }
    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie", 319);
        assertEquals(initialMenuSize + 1, restaurant.getMenu().size());
    }

    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize - 1, restaurant.getMenu().size());
    }

    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        assertThrows(itemNotFoundException.class,
                () -> restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
/*
    @Test
    public void order_value_should_be_438_when_items_are_sweet_corn_soup_and_sizzling_brownie() {
        // Adding the Sizzling brownie to the menu
        restaurant.addToMenu("Sizzling brownie", 319);

        int orderValue = restaurant.calculateOrderValue(Arrays.asList("Sweet corn soup", "Sizzling brownie"));
        assertEquals(438, orderValue);
    }

    @Test
    public void order_value_should_be_707_when_items_are_all_in_menu() {
        // Adding the Sizzling brownie to the menu
        restaurant.addToMenu("Sizzling brownie", 319);

        int orderValue = restaurant.calculateOrderValue(Arrays.asList("Sweet corn soup", "Vegetable lasagne", "Sizzling brownie"));
        assertEquals(707, orderValue);
    }*/
}
