package com.example.takondwakakusa.androidbackgroundcomm;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.core.deps.guava.base.CharMatcher.is;
import static android.support.test.espresso.core.deps.guava.base.Predicates.equalTo;
import static android.support.test.espresso.core.deps.guava.base.Predicates.instanceOf;
import static android.support.test.espresso.matcher.CursorMatchers.withRowString;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static java.util.EnumSet.allOf;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.core.StringStartsWith.startsWith;
import static org.hamcrest.object.HasToString.hasToString;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class FrontEndTest {
    private String stringToBeTyped;
    private String stringToBeSent;
    private String stringTest;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Before
    public void initValidString() {
        // Specify a valid string.
        stringToBeTyped = "Let's test whether this app successfully limits the character count of the user input text. " +
                "Blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah";
        stringToBeSent = "Let's test whether this app successfully limits the character count of the user input text. " +
                "Blah blah blah blah blah blah blah blah blah bl"; //stringToBeTyped capped at 140 characters
        stringTest = "Test";
    }

    @Test
    public void test_send_message() {
        onView(withId(R.id.channel_intro)).perform(click());
        onView(withId(R.id.messagebox)).perform(typeText(stringTest), closeSoftKeyboard());
        onView(withId(R.id.sendbutton)).perform(click());
        onData(hasToString(stringTest)).inAdapterView(withId(R.id.messageview));
    }

    @Test
    public void test_load_messages() {
        onView(withId(R.id.channel_intro)).perform(click());
        for(int i = 1; i <= 10; i++) {
            String counter = String.valueOf(i);
            onView(withId(R.id.messagebox)).perform(typeText(stringTest+counter), closeSoftKeyboard());
            onView(withId(R.id.sendbutton)).perform(click());
        }
        for(int i = 1; i <= 10; i++) {
            String counter = String.valueOf(i);
            onData(hasToString(stringTest+counter)).inAdapterView(withId(R.id.messageview));
        }
    }

    @Test
    public void test_load_more_messages() {
        onView(withId(R.id.channel_intro)).perform(click());
        for(int i = 11; i <= 20; i++) {
            String counter = String.valueOf(i);
            onView(withId(R.id.messagebox)).perform(typeText(stringTest+counter), closeSoftKeyboard());
            onView(withId(R.id.sendbutton)).perform(click());
        }
        onView(withId(R.id.loadbutton)).perform(click());
        for(int i = 1; i <= 20; i++) {
            String counter = String.valueOf(i);
            onData(hasToString(stringTest+counter)).inAdapterView(withId(R.id.messageview));
        }
    }

    @Test
    public void test_refresh_messages() {
        onView(withId(R.id.channel_intro)).perform(click());
        for(int i = 1; i <= 10; i++) {
            String counter = String.valueOf(i);
            onView(withId(R.id.messagebox)).perform(typeText(stringTest+counter), closeSoftKeyboard());
            onView(withId(R.id.sendbutton)).perform(click());
        }
        onView(withId(R.id.refreshbutton)).perform(click());
        for(int i = 1; i <= 10; i++) {
            String counter = String.valueOf(i);
            onData(hasToString(stringTest+counter)).inAdapterView(withId(R.id.messageview));
        }
    }

    @Test
    public void test_maximum_140_character_input() {
        onView(withId(R.id.channel_intro)).perform(click());
        onView(withId(R.id.messagebox)).perform(typeText(stringToBeTyped), closeSoftKeyboard());
        onView(withId(R.id.sendbutton)).perform(click());
        onData(hasToString(stringToBeSent)).inAdapterView(withId(R.id.messageview));
    }
}

