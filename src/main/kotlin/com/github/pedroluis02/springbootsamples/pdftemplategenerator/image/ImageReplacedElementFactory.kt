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

    private val imageSourceAttribute = "data-src"

    override fun createReplacedElement(
        layoutContext: LayoutContext,
        blockBox: BlockBox,
        userAgentCallback: UserAgentCallback,
        cssWidth: Int,
        cssHeight: Int
    ): ReplacedElement? {
        if (blockBox.element == null) {
            return null
        }

        val dataSource = extractImageSource(blockBox.element!!)
        return dataSource?.let {
            createImageElement(it, cssWidth, cssHeight)
        } ?: superFactory.createReplacedElement(layoutContext, blockBox, userAgentCallback, cssWidth, cssHeight)
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

    private fun createImageElement(dataSource: String, cssWidth: Int, cssHeight: Int): ITextImageElement {
        try {
            val pathResource = ClassPathResource(dataSource)
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

    private fun extractImageSource(element: Element): String? {
        val nodeName = element.nodeName
        val className = element.getAttribute("class")

        if ("div" == nodeName && className.contains("img")) {
            if (element.hasAttribute(imageSourceAttribute)) {
                return element.getAttribute(imageSourceAttribute)
            } else {
                throw RuntimeException(
                    "An element with `div` is missing a `$imageSourceAttribute` attribute indicating the media file."
                )
            }
        }
        return null
    }
}
