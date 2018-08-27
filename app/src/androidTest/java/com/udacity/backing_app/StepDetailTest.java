package com.udacity.backing_app;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.udacity.backing_app.recipeList.view.MainListActivity;
import org.junit.*;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.*;

@RunWith(AndroidJUnit4.class)
public class StepDetailTest {

    private static final int ITEM_POSITION = 0;

    @Rule
    public ActivityTestRule<MainListActivity> activityTestRule
            = new ActivityTestRule<>(MainListActivity.class);

    @Before
    public void init() {
        onView(withId(R.id.recipe_list_rv))
                .perform(actionOnItemAtPosition(ITEM_POSITION, click()));
        onView(withId(R.id.step_list_rv))
                .perform(actionOnItemAtPosition(ITEM_POSITION, click()));
    }

    @Test
    public void stepPageDisplayedTest() {
        onView(withId(R.id.step_video_player))
                .check(matches(isDisplayed()));
        onView(withId(R.id.instructions_text_tv))
                .check(matches(withText("Recipe Introduction")));
    }
}
