package greg.gfx.swing

import greg.gfx.RenderManager
import greg.gfx.Renderable
import greg.gfx.Renderer2D
import java.awt.*
import java.awt.image.BufferedImage
import javax.swing.JComponent

class GraphicsRenderer2D(val g : Graphics, override val size : Dimension) : Renderer2D{
    override var color: Color
        get() = g.color
        set(x) {g.color = x}

    override fun drawRect(pos: Point, s: Dimension) {
        g.drawRect(pos.x,pos.y,s.width,s.height)
    }

    override fun drawEllipse(pos: Point, s: Dimension) {
        g.drawArc(pos.x+s.width/2,pos.y+s.height/2,s.width,s.height,0,360)
    }

    override fun drawLine(a: Point, b: Point) {
        g.drawLine(a.x,a.y,b.x,b.y)
    }

    override fun fillRect(pos: Point, s: Dimension) {
        g.fillRect(pos.x,pos.y,s.width,s.height)
    }

    override fun fillEllipse(pos: Point, s: Dimension) {
        g.fillArc(pos.x,pos.y,s.width,s.height,0,360)
    }

    override fun fillBackground() {
        g.fillRect(0,0,size.width,size.height)
    }

    override fun drawImage(i: Image, pos: Point) {
        g.drawImage(i,pos.x,pos.y,null)
    }

    override fun drawImage(i: Image, pos: Point, s: Dimension) {
        g.drawImage(i,pos.x,pos.y,s.width,s.height,null)
    }

}

class GraphicsRenderManager(val c : JComponent) : RenderManager<GraphicsRenderer2D>(){
    private lateinit var i : Image

    override fun render(r: Renderable<GraphicsRenderer2D>) {
        r.render(GraphicsRenderer2D(i.graphics,c.size))
    }

    override fun preRender() {
        i = BufferedImage(c.width,c.height,BufferedImage.TYPE_4BYTE_ABGR)
    }

    override fun postRender() {
        c.graphics.drawImage(i,0,0,c.width,c.height,null)
    }
    override fun setup() {

    }

    override fun cleanup() {

    }

}