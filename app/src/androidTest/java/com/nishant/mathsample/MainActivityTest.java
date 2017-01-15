package com.nishant.mathsample;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Toast;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

  private static final Long waitDuration = 2800L;

  @Rule
  public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

  @Test
  public void formula_1_test() throws Throwable {
    onView(withId(R.id.input_view))
      .perform(typeText("`sum_(i=1)^n i^3=((n(n+1))/2)^2`"))
      .perform(closeSoftKeyboard());
    showMessage("ASCII math rendering");
    Thread.sleep(waitDuration);
  }

  @Test
  public void formula_2_test() throws Throwable {
    onView(withId(R.id.input_view))
      .perform(typeText("`2+3 = 5`$$ 5/30 + x = 99$$"))
      .perform(closeSoftKeyboard());
    showMessage("Multiline rendering");
    Thread.sleep(waitDuration);
  }

  @Test
  public void formula_3_test() throws Throwable {
    onView(withId(R.id.input_view))
      .perform(
        typeText("$\\\\vec{F} = \\\\frac{d \\\\vec{p}}{dt} = m \\\\frac{d \\\\vec{v}}{dt} = m \\\\vec{a}$")
      )
      .perform(closeSoftKeyboard());
    showMessage("Latex rendering");
    Thread.sleep(waitDuration);
  }

  @Test
  public void mainActivityTest() throws Throwable {
    ViewInteraction webView = onView(
      allOf(childAtPosition(
        allOf(withId(R.id.activity_main),
          childAtPosition(
            withId(android.R.id.content),
            0)),
        0),
        isDisplayed()));
    webView.check(matches(isDisplayed()));

    ViewInteraction editText = onView(
      allOf(withId(R.id.input_view),
        childAtPosition(
          allOf(withId(R.id.activity_main),
            childAtPosition(
              withId(android.R.id.content),
              0)),
          1),
        isDisplayed()));
    editText.check(matches(isDisplayed()));
  }

  private void showMessage(final String message) throws Throwable {
    mActivityTestRule.runOnUiThread(new Runnable() {
      @Override
      public void run() {
        Toast.makeText(mActivityTestRule.getActivity().getApplicationContext(), message, Toast.LENGTH_SHORT).show();

      }
    });
  }

  private static Matcher<View> childAtPosition(
    final Matcher<View> parentMatcher, final int position
  ) {

    return new TypeSafeMatcher<View>() {
      @Override
      public void describeTo(Description description) {
        description.appendText("Child at position " + position + " in parent ");
        parentMatcher.describeTo(description);
      }

      @Override
      public boolean matchesSafely(View view) {
        ViewParent parent = view.getParent();
        return parent instanceof ViewGroup && parentMatcher.matches(parent)
          && view.equals(((ViewGroup) parent).getChildAt(position));
      }
    };
  }
}
