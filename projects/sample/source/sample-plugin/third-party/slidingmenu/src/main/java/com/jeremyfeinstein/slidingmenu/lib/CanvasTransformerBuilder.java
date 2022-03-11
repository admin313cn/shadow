package com.jeremyfeinstein.slidingmenu.lib;

import android.graphics.Canvas;
import android.view.animation.Interpolator;

public class CanvasTransformerBuilder {

    private static Interpolator lin = new Interpolator() {
        public float getInterpolation(float t) {
            return t;
        }
    };



}
