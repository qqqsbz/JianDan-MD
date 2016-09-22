package com.example.coder.jiandan_md.util;

import com.example.coder.jiandan_md.model.HTMLElement;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by coder on 16/9/19.
 */
public class HTMLParser {

    public static List<HTMLElement> parseContent(String content) {

        Document document = Jsoup.parse(content);

        Elements elements = document.getElementsByTag("body");

        List<HTMLElement> htmlElements = new ArrayList<HTMLElement>();

        for (Node node : elements.first().childNodes()) {

            HTMLElement htmlElement = new HTMLElement();

            List<Node> nodes = new ArrayList<Node>();

            nodes.add(node);

            parseNodes(nodes,htmlElement,htmlElements);

            if (htmlElement.getType() != null && htmlElement.getTextBuffer().toString().trim().length() > 0) {

                htmlElements.add(htmlElement);
            }

        }

        return htmlElements;

    }

    private static void parseNodes(List<Node> nodes,HTMLElement htmlElement,List<HTMLElement> htmlElements) {

        for (Node node : nodes ) {

            if (node instanceof Element) {

                Element nodeElement = (Element) node;

                if (nodeElement.tagName().equals("img")) {

                    HTMLElement imageElement = new HTMLElement();

                    imageElement.setType(HTMLElement.ElementType.Image);

                    imageElement.setLink(nodeElement.attr("src"));

                    htmlElements.add(imageElement);

                } else if (nodeElement.tagName().equals("em")){

                    String content = "<em>";

                    for (Node node1 : nodeElement.childNodes()) {

                        if (node1 instanceof Element) {

                            Element node1Element = (Element) node1;

                            String linkText = "";

                            if (node1Element.tagName().equals("a")) {

                                if (node1Element.childNodes().size() > 0) {

                                    for (Node node2 : node1Element.childNodes()) {

                                        if (node2 instanceof Element) {

                                            parseNodes(node2.childNodes(),htmlElement,htmlElements);

                                        } else if (node2 instanceof TextNode){

                                            linkText = ((TextNode) node2).text();

                                        }

                                    }
                                }

                                content += ("<a href=\"" + node1Element.attr("href") + "\">" + linkText + "</a>");

                            }

                            parseNodes(node1.childNodes(),htmlElement,htmlElements);

                            String replaceText = htmlElement.getTextBuffer().toString().replace(linkText,"");

                            htmlElement.setTextBuffer(new StringBuffer().append(replaceText));

                        } else if (node1 instanceof TextNode){

                            content += ((TextNode) node1).text();

                        }

                    }

                    htmlElement.getTextBuffer().append(content + "</em>");

                    htmlElement.setType(HTMLElement.ElementType.Text);


                } else if (nodeElement.tagName().equals("strong")) {

                    htmlElement.getTextBuffer().append(((TextNode) nodeElement.childNodes().get(0)).text());

                    htmlElement.setType(HTMLElement.ElementType.Heade);

                } else if (nodeElement.tagName().equals("div")) {

                    for (Node node1 : nodeElement.childNodes()) {

                        if (node1 instanceof Element) {

                            Element divElement = (Element) node1;

                            if (divElement.tagName().equals("a")) {

                                HTMLElement linkElement = new HTMLElement();

                                if (divElement.attr("class").equals("share-link share-link-weibo")) {

                                    linkElement.setType(HTMLElement.ElementType.ShareToSina);

                                    linkElement.setLink(divElement.attr("href"));

                                } else if (divElement.attr("class").equals("share-link share-link-weixin")) {

                                    linkElement.setType(HTMLElement.ElementType.ShareToWeChat);

                                    for (Node weChatNode : divElement.childNodes()) {

                                        if (weChatNode instanceof Element) {

                                            Element weChatElement = (Element) weChatNode;

                                            linkElement.setLink(weChatElement.attr("src"));

                                        }

                                    }

                                }

                                htmlElements.add(linkElement);
                            }
                        }
                    }

                    continue;

                }

                parseNodes(node.childNodes(),htmlElement,htmlElements);

            } else if (node instanceof TextNode) {

                if (htmlElement.getTextBuffer().toString().indexOf(((TextNode) node).text()) == -1) {

                    htmlElement.setType(HTMLElement.ElementType.Text);

                    htmlElement.getTextBuffer().append(((TextNode) node).text());

                }

            }

        }

    }
}
