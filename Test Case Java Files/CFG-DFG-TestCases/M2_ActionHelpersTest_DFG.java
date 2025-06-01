package main.java.com.stfalcon.chatkit.utils;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import android.content.Context;
import androidx.core.content.ContextCompat;

import org.junit.Test;
import org.mockito.MockedStatic;

public class M2_ActionHelpersTest_DFG {

    /* TC1: getIconGrayedOutColor uses context to fetch gray color
     * Target Node: Node 1 (context usage)
     */
    @Test
    public void testCase1() {
        Context mockContext = mock(Context.class);

        try (MockedStatic<ContextCompat> mockedStatic = mockStatic(ContextCompat.class)) {
            mockedStatic.when(() -> ContextCompat.getColor(eq(mockContext), anyInt()))
                        .thenReturn(0xCCCCCC);  // mocked gray color

            int result = ActionHelpers.getIconGrayedOutColor(mockContext);
            assertEquals(0xCCCCCC, result);
        }
    }
}
