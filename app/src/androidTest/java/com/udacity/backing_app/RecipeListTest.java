package com.udacity.backing_app;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.udacity.backing_app.recipeList.view.MainListActivity;
import org.hamcrest.*;
import org.junit.*;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.CoreMatchers.allOf;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class RecipeListTest {

    @Rule
    public ActivityTestRule<MainListActivity> activityTestRule
            = new ActivityTestRule<>(MainListActivity.class);


    @Test
    public void notEmptyRecipeListTest() {
        final int ITEM_COUNT[] = new int[1];
        Matcher<View> matcher = new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(View item) {
                ITEM_COUNT[0] = ((RecyclerView) item).getAdapter().getItemCount();
                return true;
            }
            @Override
            public void describeTo(Description description) {}
        };
        onView(allOf(withId(R.id.recipe_list_rv), isDisplayed())).check(matches(matcher));
        assertTrue(ITEM_COUNT[0] > 0);
    }
}