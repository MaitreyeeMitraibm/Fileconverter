package Filehandling;

import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class XmlToJsonConverter {

    // Method to print details of a student from the XML file based on the student ID and output format choice
    public void printStudentDetails(String filePath, int studentId, int choice) {
        try {
            // Parsing the XML file using DOM parser
            File xmlFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            doc.getDocumentElement().normalize();

            // Getting the list of student elements from the XML document
            NodeList nodeList = doc.getElementsByTagName("student");

            // Iterating through the student elements to find the student with the specified ID
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    int id = Integer.parseInt(element.getAttribute("id"));
                    if (id == studentId) {
                        // If the choice is XML, print student details in XML format
                        if (choice == 2) {
                            System.out.println("Student details in XML format:");
                            System.out.println(nodeToString(node));
                        }
                        // If the choice is JSON, construct a JSON object with student details and print it
                        else if (choice == 1) {
                            JSONObject studentDetails = new JSONObject();
                            studentDetails.put("name", element.getElementsByTagName("name").item(0).getTextContent());
                            studentDetails.put("age", Integer.parseInt(element.getElementsByTagName("age").item(0).getTextContent()));
                            System.out.println("Student details in JSON format:");
                            System.out.println(studentDetails.toString());
                        } else {
                            System.out.println("Invalid output format choice.");
                        }
                        return;
                    }
                }
            }
            System.out.println("Student not found.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Helper method to convert a Node to String
    private String nodeToString(Node node) {
    	System.out.println(node);
        StringBuilder sb = new StringBuilder();
        sb.append("<").append(node.getNodeName()).append(">");
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node childNode = childNodes.item(i);
            if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                sb.append(nodeToString(childNode));
            } else if (childNode.getNodeType() == Node.TEXT_NODE) {
                sb.append(childNode.getNodeValue());
            }
        }
        sb.append("</").append(node.getNodeName()).append(">");
        return sb.toString();
    }
}
