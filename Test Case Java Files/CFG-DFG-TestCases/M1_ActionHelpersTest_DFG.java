package java.com.liskovsoft.smartyoutubetv2.common.proxy;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import android.content.Context;
import android.content.res.Resources.Theme;
import android.util.TypedValue;
import androidx.core.content.ContextCompat;
import org.junit.Test;

public class M1_ActionHelpersTest_DFG {

    // Test Path 1: {1, 2, 3}
    @Test
    public void testPath1_Resolved() {
        Context context = mock(Context.class);
        Theme theme = mock(Theme.class);
        when(context.getTheme()).thenReturn(theme);

        when(theme.resolveAttribute(anyInt(), any(TypedValue.class), eq(true)))
            .thenAnswer(invocation -> {
                TypedValue value = invocation.getArgument(1);
                value.data = 11111; // fake color
                return true;
            });

        int result = ActionHelpers.getIconHighlightColor(context);
        assertEquals(11111, result);
    }

    // Test Path 2: {1, 2, 4}
    @Test
    public void testPath2_NotResolved() {
        Context context = mock(Context.class);
        Theme theme = mock(Theme.class);
        when(context.getTheme()).thenReturn(theme);
        when(theme.resolveAttribute(anyInt(), any(TypedValue.class), eq(true))).thenReturn(false);

        // Simulate fallback value
        mockStatic(ContextCompat.class).when(() ->
            ContextCompat.getColor(context, R.color.lb_playback_icon_highlight_no_theme)
        ).thenReturn(22222);

        int result = ActionHelpers.getIconHighlightColor(context);
        assertEquals(22222, result);
    }
}
