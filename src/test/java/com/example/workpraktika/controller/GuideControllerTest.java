package com.example.workpraktika.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GuideControllerTest {

    private final GuideController guideController = new GuideController();

    @Test
    void showGuidePage_returnsGuideView() {
        String view = guideController.showGuidePage();
        assertEquals("guide", view, "Должен вернуть страницу 'guide'");
    }

    @Test
    void showAboutPage_returnsAboutView() {
        String view = guideController.showAboutPage();
        assertEquals("about", view, "Должен вернуть страницу 'about'");
    }
}
