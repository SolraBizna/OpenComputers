package li.cil.oc.client.renderer.block

import li.cil.oc.common.tileentity
import li.cil.oc.util.ExtendedAABB._
import net.minecraft.block.Block
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.RenderBlocks
import net.minecraft.util.IIcon

object Print {
  def render(print: tileentity.Print, x: Int, y: Int, z: Int, block: Block, renderer: RenderBlocks): Unit = {
    for (shape <- if (print.state) print.data.stateOn else print.data.stateOff) {
      val bounds = shape.bounds.rotateTowards(print.facing)
      renderer.setOverrideBlockTexture(resolveTexture(shape.texture))
      renderer.setRenderBounds(
        bounds.minX, bounds.minY, bounds.minZ,
        bounds.maxX, bounds.maxY, bounds.maxZ)
      renderer.renderStandardBlock(block, x, y, z)
    }
    renderer.clearOverrideBlockTexture()
  }

  def resolveTexture(name: String): IIcon =
    Option(Minecraft.getMinecraft.getTextureMapBlocks.getTextureExtry(name)).
      getOrElse(Minecraft.getMinecraft.getTextureMapBlocks.getTextureExtry("wool_colored_magenta"))
}
