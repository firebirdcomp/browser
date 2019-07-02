/*
 * Project by Firebird. All rigths reserved to Felpi and Luis.
 */
package Interpreter;

import java.util.ArrayList;

public abstract class Node {
    public Node father = null;
    public int level;
    public ArrayList<Atributtes> atributtes = new ArrayList();
}
