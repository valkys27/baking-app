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
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.CoreMatchers.allOf;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class RecipeDetailTest {

    private static final String RECIPE_ITEM_NAME = "Nutella Pie";
    private static final int RECIPE_ITEM_POSITION = 0;

    @Rule
    public ActivityTestRule<MainListActivity> activityTestRule
            = new ActivityTestRule<>(MainListActivity.class);

    @Before
    public void init() {
        onView(withId(R.id.recipe_list_rv))
                .perform(actionOnItemAtPosition(RECIPE_ITEM_POSITION, click()));
    }

    @Test
    public void recipePageDisplayedTest() {
        onView(withText(RECIPE_ITEM_NAME))
                .check(matches(isDisplayed()));
        onView(withId(R.id.ingredient_list_tv))
                .check(matches(isDisplayed()));
        onView(withId(R.id.step_list_rv))
                .check(matches(isDisplayed()));
    }

    @Test
    public void notEmptyStepListTest() {
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
        onView(allOf(withId(R.id.step_list_rv), isDisplayed())).check(matches(matcher));
        assertTrue(ITEM_COUNT[0] > 0);
    }
}
