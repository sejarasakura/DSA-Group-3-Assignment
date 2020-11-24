/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cilent.pages;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author ITSUKA KOTORI
 */
public abstract class AbstractPage {

    /**
     * To build html pages use
     */
    protected final StringBuilder stringBuilder = new StringBuilder();

    /**
     * Must have servlet
     */
    protected final HttpServletRequest request;

    /**
     * Generate HTML
     *
     * @return
     */
    public abstract String getHtml();

    /**
     * Generate HTML
     *
     * @return
     */
    public abstract adt.ArrList<String> getHtmls();

    /**
     * Constructor force user extends
     *
     * @param request
     */
    public AbstractPage(HttpServletRequest request) {
        this.request = request;
    }
}
