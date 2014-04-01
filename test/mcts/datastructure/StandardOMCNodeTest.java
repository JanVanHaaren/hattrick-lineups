package mcts.datastructure;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

public class StandardOMCNodeTest {

	private StandardOMCNode parentNode;
	private StandardOMCNode childNode1;
	private StandardOMCNode childNode2;
	private StandardOMCNode childNode3;
	private StandardOMCNode childOf3Node1;
	private StandardOMCNode childOf3Node2;
	
	@Before
	public void setUp() throws Exception {
		ChoiceSet choiceSetMock = mock(ChoiceSet.class);
		
		parentNode = new StandardOMCNode(null, choiceSetMock);
		childNode1 = new StandardOMCNode(parentNode, choiceSetMock);
		childNode2 = new StandardOMCNode(parentNode, choiceSetMock);
		childNode3 = new StandardOMCNode(parentNode, choiceSetMock);
		childOf3Node1 = new StandardOMCNode(childNode3, choiceSetMock);
		childOf3Node2 = new StandardOMCNode(childNode3, choiceSetMock);
	}

	@Test
	public void testGetUrgency() {
		childNode1.backPropagate(-1);
		childNode2.backPropagate(1);
		childNode2.backPropagate(5);
		childNode3.backPropagate(0.5);
		childOf3Node1.backPropagate(0);
		childOf3Node2.backPropagate(1);
		
		assertEquals(1.24419E-15, childNode1.getUrgency((OMCVariantNode) childNode1.getMaxSibling()), 1E-16);
		assertEquals(1, childNode2.getUrgency((OMCVariantNode) childNode2.getMaxSibling()), 0.00001);
		assertEquals(9.14130E-10, childNode3.getUrgency((OMCVariantNode) childNode3.getMaxSibling()), 1E-11);
		assertEquals(0.0455003, childOf3Node1.getUrgency((OMCVariantNode) childOf3Node1.getMaxSibling()), 0.00001);
		assertEquals(1, childOf3Node2.getUrgency((OMCVariantNode) childOf3Node2.getMaxSibling()), 0.00001);
		
	}

}
