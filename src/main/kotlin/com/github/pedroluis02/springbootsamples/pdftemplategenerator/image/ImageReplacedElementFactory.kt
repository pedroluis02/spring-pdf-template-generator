package com.github.pedroluis02.springbootsamples.pdftemplategenerator.image

import com.lowagie.text.Image
import org.springframework.core.io.ClassPathResource
import org.w3c.dom.Element
import org.xhtmlrenderer.extend.FSImage
import org.xhtmlrenderer.extend.ReplacedElement
import org.xhtmlrenderer.extend.ReplacedElementFactory
import org.xhtmlrenderer.extend.UserAgentCallback
import org.xhtmlrenderer.layout.LayoutContext
import org.xhtmlrenderer.pdf.ITextFSImage
import org.xhtmlrenderer.pdf.ITextImageElement
import org.xhtmlrenderer.render.BlockBox
import org.xhtmlrenderer.simple.extend.FormSubmissionListener

class ImageReplacedElementFactory(private val superFactory: ReplacedElementFactory) : ReplacedElementFactory {
    override fun createReplacedElement(
        layoutContext: LayoutContext,
        blockBox: BlockBox,
        userAgentCallback: UserAgentCallback,
        cssWidth: Int,
        cssHeight: Int
    ): ReplacedElement? {
        val element = blockBox.element ?: return null
        val nodeName = element.nodeName
        val className = element.getAttribute("class")
        if ("div" == nodeName && className.contains("img")) {
            if (!element.hasAttribute("data-src")) {
                throw RuntimeException("An element with class `media` is missing a `data-src` attribute indicating the media file.")
            }

            try {
                val pathResource = ClassPathResource(element.getAttribute("data-src"))
                val bytes = pathResource.contentAsByteArray
                val image = Image.getInstance(bytes)
                val fsImage: FSImage = ITextFSImage(image)
                if ((cssWidth != -1) || (cssHeight != -1)) {
                    fsImage.scale(cssWidth, cssHeight)
                }
                return ITextImageElement(fsImage)
            } catch (e: Exception) {
                throw RuntimeException("There was a problem trying to read a template embedded graphic.", e)
            }
        }
        return superFactory.createReplacedElement(layoutContext, blockBox, userAgentCallback, cssWidth, cssHeight)
    }

    override fun reset() {
        superFactory.reset()
    }

    override fun remove(e: Element) {
        superFactory.remove(e)
    }

    override fun setFormSubmissionListener(listener: FormSubmissionListener) {
        superFactory.setFormSubmissionListener(listener)
    }
}
