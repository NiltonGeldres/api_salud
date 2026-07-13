package com.api_salud.api_salud.service;

import org.xhtmlrenderer.extend.ReplacedElement;
import org.xhtmlrenderer.extend.ReplacedElementFactory;
import org.xhtmlrenderer.extend.UserAgentCallback;
import org.xhtmlrenderer.layout.LayoutContext;
import org.xhtmlrenderer.pdf.ITextImageElement;
import org.xhtmlrenderer.pdf.ITextOutputDevice;
import org.xhtmlrenderer.render.BlockBox;
import org.xhtmlrenderer.simple.extend.FormSubmissionListener;
import org.w3c.dom.Element;

public class BoundedReplacedElementFactory implements ReplacedElementFactory {
	private final ReplacedElementFactory superFactory;

    public BoundedReplacedElementFactory(ReplacedElementFactory superFactory) {
        this.superFactory = superFactory;
    }

    @Override
    public ReplacedElement createReplacedElement(LayoutContext c, BlockBox box, UserAgentCallback uac, int cssWidth, int cssHeight) {
        Element e = box.getElement();
        if (e == null) return null;
        String nodeName = e.getNodeName();
        if (nodeName.equals("img")) {
            // Aquí Flying Saucer carga la imagen
            return superFactory.createReplacedElement(c, box, uac, cssWidth, cssHeight);
        }
        return superFactory.createReplacedElement(c, box, uac, cssWidth, cssHeight);
    }

    @Override
    public void reset() {
        superFactory.reset();
    }

    @Override
    public void remove(Element e) {
        superFactory.remove(e);
    }

	@Override
	public void setFormSubmissionListener(FormSubmissionListener listener) {
		// TODO Auto-generated method stub
		
	}
}