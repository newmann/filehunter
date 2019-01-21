package com.beiyelin.filehunter.view;

import de.felixroske.jfxsupport.SplashScreen;

/**
 * @Author: newmann hu
 * @Date: created 20:16 2019-01-21
 * @Description:
 **/
public class CustomSplash extends SplashScreen {
    /**
     * Use your own splash image instead of the default one
     *
     * @return "/splash/javafx.png"
     */
    @Override
    public String getImagePath() {
        return super.getImagePath();
    }

    /**
     * Customize if the splash screen should be visible at all
     *
     * @return true by default
     */
    @Override
    public boolean visible() {
        return super.visible();
    }
}
