package ISP5;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.util.TypedValue;
import androidx.core.content.ContextCompat;
import org.mockito.Mockito;

public class ActionHelpersTest {
    private Context mockContext;
    private Theme mockTheme;

    @Before
    public void setup() {
        mockContext = Mockito.mock(Context.class);
        mockTheme = Mockito.mock(Theme.class);
        Mockito.when(mockContext.getTheme()).thenReturn(mockTheme);
    }

    /**
     * Test case: Valid Context with a themed attribute
     * Expected result: The returned color matches the theme-defined color.
     */
    @Test
    public void testGetIconHighlightColor_withThemedAttribute() {
        TypedValue outValue = new TypedValue();
        outValue.data = 0xFF00FF; // Mock color value
        
        Mockito.when(mockTheme.resolveAttribute(R.attr.playbackControlsIconHighlightColor, outValue, true))
               .thenReturn(true);

        int result = ActionHelpers.getIconHighlightColor(mockContext);
        assertEquals(0xFF00FF, result);
    }

    /**
     * Test case: Valid Context without the themed attribute
     * Expected result: The fallback color is returned.
     */
    @Test
    public void testGetIconHighlightColor_withoutThemedAttribute() {
        Mockito.when(mockTheme.resolveAttribute(Mockito.anyInt(), Mockito.any(TypedValue.class), Mockito.eq(true)))
               .thenReturn(false);
        Mockito.when(ContextCompat.getColor(mockContext, R.color.lb_playback_icon_highlight_no_theme)).thenReturn(0x00FFFF);

        int result = ActionHelpers.getIconHighlightColor(mockContext);
        assertEquals(0x00FFFF, result);
    }

    /**
     * Test case: Null Context provided
     * Expected result: NullPointerException
     */
    @Test
    public void testGetIconHighlightColor_nullContext() {
        assertThrows(NullPointerException.class, () -> ActionHelpers.getIconHighlightColor(null));
    }

    /**
     * Test case: Valid Context
     * Expected result: Returns the gray color defined in resources.
     */
    @Test
    public void testGetIconGrayedOutColor_validContext() {
        Mockito.when(ContextCompat.getColor(mockContext, R.color.gray)).thenReturn(0x808080);

        int result = ActionHelpers.getIconGrayedOutColor(mockContext);
        assertEquals(0x808080, result);
    }

    /**
     * Test case: Null Context provided
     * Expected result: NullPointerException
     */
    @Test
    public void testGetIconGrayedOutColor_nullContext() {
        assertThrows(NullPointerException.class, () -> ActionHelpers.getIconGrayedOutColor(null));
    }
}


