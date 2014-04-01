package mcts.datastructure;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

public class SinglePlayerOMCNodeTest {

	private SinglePlayerOMCNode parentNode;
	private SinglePlayerOMCNode childNode1;
	private SinglePlayerOMCNode childNode2;
	private SinglePlayerOMCNode childNode3;
	private SinglePlayerOMCNode childOf3Node1;
	private SinglePlayerOMCNode childOf3Node2;
	
	@Before
	public void setUp() throws Exception {
		ChoiceSet choiceSetMock = mock(ChoiceSet.class);
		int g = 2;
		
		parentNode = new SinglePlayerOMCNode(null, choiceSetMock, g);
		childNode1 = new SinglePlayerOMCNode(parentNode, choiceSetMock, g);
		childNode2 = new SinglePlayerOMCNode(parentNode, choiceSetMock, g);
		childNode3 = new SinglePlayerOMCNode(parentNode, choiceSetMock, g);
		childOf3Node1 = new SinglePlayerOMCNode(childNode3, choiceSetMock, g);
		childOf3Node2 = new SinglePlayerOMCNode(childNode3, choiceSetMock, g);
	}

	@Test
	public void testGetUrgency() {
		childNode1.backPropagate(-1);
		childNode2.backPropagate(1);
		childNode2.backPropagate(5);
		childNode3.backPropagate(0.5);
		childOf3Node1.backPropagate(0);
		childOf3Node2.backPropagate(1);
		
		assertEquals(1.27775E-57, childNode1.getUrgency((OMCVariantNode) childNode1.getMaxSibling()), 1E-58);
		assertEquals(1, childNode2.getUrgency((OMCVariantNode) childNode2.getMaxSibling()), 0.00001);
		assertEquals(1.73364E-34, childNode3.getUrgency((OMCVariantNode) childNode3.getMaxSibling()), 1E-35);
		assertEquals(0.0000633425, childOf3Node1.getUrgency((OMCVariantNode) childOf3Node1.getMaxSibling()), 0.000001);
		assertEquals(1, childOf3Node2.getUrgency((OMCVariantNode) childOf3Node2.getMaxSibling()), 0.00001);	
	}

}
