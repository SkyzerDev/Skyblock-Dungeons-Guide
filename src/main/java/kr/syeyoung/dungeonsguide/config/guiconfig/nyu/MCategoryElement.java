/*
 * Dungeons Guide - The most intelligent Hypixel Skyblock Dungeons Mod
 * Copyright (C) 2021  cyoung06
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package kr.syeyoung.dungeonsguide.config.guiconfig.nyu;

import kr.syeyoung.dungeonsguide.gui.MPanel;
import kr.syeyoung.dungeonsguide.utils.RenderUtils;
import kr.syeyoung.dungeonsguide.utils.cursor.EnumCursor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.function.Consumer;

public class MCategoryElement extends MPanel {
    private String category;
    private Runnable onClick;
    private int leftPad = 0;
    private int offsetX;
    private RootConfigPanel rootConfigPanel;
    public MCategoryElement(String category, Runnable onClick, int leftPad, int offsetX, RootConfigPanel rooot) {
        this.category = category;
        this.onClick = onClick;
        this.leftPad = leftPad;
        this.offsetX = offsetX;
        this.rootConfigPanel = rooot;
    }

    @Override
    public void render(int absMousex, int absMousey, int relMousex0, int relMousey0, float partialTicks, Rectangle scissor) {
        if (rootConfigPanel.getCurrentPage().equals(category)) {
            GL11.glDisable(GL11.GL_SCISSOR_TEST);
            Gui.drawRect(leftPad - offsetX, 0, getBounds().width, getBounds().height, RenderUtils.blendAlpha(0x141414, 0.13f));
            GL11.glEnable(GL11.GL_SCISSOR_TEST);
        } else if (lastAbsClip.contains(absMousex, absMousey)) {
            GL11.glDisable(GL11.GL_SCISSOR_TEST);
            Gui.drawRect(leftPad - offsetX, 0, getBounds().width, getBounds().height, RenderUtils.blendAlpha(0x141414, 0.09f));
            GL11.glEnable(GL11.GL_SCISSOR_TEST);
        }


        FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
        String name = category.substring(category.lastIndexOf(".")+1);
        fr.drawString(name, leftPad,2,-1);

    }

    @Override
    public Dimension getPreferredSize() {
        FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
        return new Dimension(fr.getStringWidth(category.substring(category.lastIndexOf(".")+1)) + leftPad+10, fr.FONT_HEIGHT+4);
    }

    @Override
    public void mouseClicked(int absMouseX, int absMouseY, int relMouseX, int relMouseY, int mouseButton) {
        if (!lastAbsClip.contains(absMouseX, absMouseY)) { return; }
        if (onClick != null) onClick.run();
    }
    @Override
    public void mouseMoved(int absMouseX, int absMouseY, int relMouseX0, int relMouseY0) {
        if (lastAbsClip.contains(absMouseX, absMouseY))
            setCursor(EnumCursor.POINTING_HAND);
    }
}