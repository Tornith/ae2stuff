/*
 * Copyright (c) bdew, 2014 - 2020
 * https://github.com/bdew/ae2stuff
 *
 * This mod is distributed under the terms of the MIT License.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package net.bdew.ae2stuff.misc

import appeng.api.implementations.guiobjects.{IGuiItem, INetworkTool}
import net.bdew.ae2stuff.AE2Defs
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntity

object UpgradeableHelper {
  lazy val networkTool = AE2Defs.items.networkTool()

  def isNetworkTool(is: ItemStack) = networkTool.isSameAs(is)

  def getNetworkToolObj(is: ItemStack, te: TileEntity) =
    networkTool.maybeItem().get().asInstanceOf[IGuiItem].getGuiObject(is, te.getWorld, te.getPos).asInstanceOf[INetworkTool]

  def findNetworktoolStack(player: EntityPlayer) = (for {
    i <- 0 until player.inventory.getSizeInventory
    stack <- Option(player.inventory.getStackInSlot(i))
    if UpgradeableHelper.networkTool.isSameAs(stack)
  } yield i).headOption
}
