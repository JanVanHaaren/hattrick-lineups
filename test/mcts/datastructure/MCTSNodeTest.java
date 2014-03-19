package mcts.datastructure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

public class MCTSNodeTest {

	private MCTSNode parentNode;
	private MCTSNode childNode1;
	private MCTSNode childNode2;
	private MCTSNode childNode3; 
	
	@Before
	public void setUp() throws Exception {
		ChoiceSet choiceSetMock = mock(ChoiceSet.class);
		
		parentNode = new MCTSNodeInstantiation(null, choiceSetMock);
		
		childNode1 = new MCTSNodeInstantiation(parentNode, choiceSetMock);
		childNode2 = new MCTSNodeInstantiation(parentNode, choiceSetMock);
		childNode3 = new MCTSNodeInstantiation(parentNode, choiceSetMock);
	}
	
	public void testGetChildren(){
		assertEquals(3, parentNode.getChildren().size());
		assertTrue(parentNode.getChildren().contains(childNode1));
		assertTrue(parentNode.getChildren().contains(childNode2));
		assertTrue(parentNode.getChildren().contains(childNode3));
	}
	
	@Test
	public void testBackPropagate(){
		childNode1.backPropagate(0.6);
		childNode2.backPropagate(0.3);
		childNode2.backPropagate(0.6);
		parentNode.backPropagate(0.9);
		
		System.out.println(childNode1.getValue());
		System.out.println(childNode2.getValue());
		System.out.println(childNode3.getValue());
		assertEquals(childNode1, parentNode.getMaxChild());
		
		assertEquals(childNode1, childNode1.getMaxSibling());
		assertEquals(childNode1, childNode2.getMaxSibling());
		assertEquals(childNode1, childNode3.getMaxSibling());
		
		assertEquals(0.6, childNode1.getMaxSiblingValue(), 0);
		assertEquals(0.6, childNode2.getMaxSiblingValue(), 0);
		assertEquals(0.6, childNode3.getMaxSiblingValue(), 0);
		
		assertEquals(4, parentNode.getVisits());
		assertEquals(1, childNode1.getVisits());
		assertEquals(2, childNode2.getVisits());
		assertEquals(1, childNode3.getVisits());
		
		assertEquals(0.6, childNode1.getValue(), 0.0000001);
		assertEquals(0.45, childNode2.getValue(), 0.0000001);
		assertEquals(0.5, childNode3.getValue(), 0.0000001);
		assertEquals(0.6, parentNode.getValue(), 0.0000001);
	}
	
	private class MCTSNodeInstantiation extends MCTSNode
	{
		
		public MCTSNodeInstantiation(MCTSNode parent, ChoiceSet choiceSet) {
			super(parent, choiceSet);
		}

		@Override
		protected MCTSNode generateChild(ChoiceSet choiceSet) {
			return new MCTSNodeInstantiation(this, choiceSet);
		}

		@Override
		protected double getSelectionValue() {
			// TODO Auto-generated method stub
			return 0;
		}
		
	}

}
