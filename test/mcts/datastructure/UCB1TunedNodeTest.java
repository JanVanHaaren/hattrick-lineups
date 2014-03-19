package mcts.datastructure;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

public class UCB1TunedNodeTest {

	private UCB1TunedNode parentNode;
	private UCB1TunedNode childNode1;
	private UCB1TunedNode childNode2;
	private UCB1TunedNode childNode3;
	private UCB1TunedNode childOf3Node1;
	private UCB1TunedNode childOf3Node2;
	
	@Before
	public void setUp() throws Exception {
		ChoiceSet choiceSetMock = mock(ChoiceSet.class);
		
		double c = 2;
		
		parentNode = new UCB1TunedNode(null, choiceSetMock, c);
		childNode1 = new UCB1TunedNode(parentNode, choiceSetMock, c);
		childNode2 = new UCB1TunedNode(parentNode, choiceSetMock, c);
		childNode3 = new UCB1TunedNode(parentNode, choiceSetMock, c);
		childOf3Node1 = new UCB1TunedNode(childNode3, choiceSetMock, c);
		childOf3Node2 = new UCB1TunedNode(childNode3, choiceSetMock, c);
	}

	@Test
	public void testGetUrgency() {
		childNode1.backPropagate(-1);
		childNode2.backPropagate(1);
		childNode2.backPropagate(5);
		childNode3.backPropagate(0.5);
		childOf3Node1.backPropagate(0);
		childOf3Node2.backPropagate(1);
		
		assertEquals(0.33856619904585, childNode1.getSelectionValue(), 0.00001);
		assertEquals(3.94650923641242, childNode2.getSelectionValue(), 0.00001);
		assertEquals(1.27282, childNode3.getSelectionValue(), 0.00001);
		assertEquals(1.04814707396820, childOf3Node1.getSelectionValue(), 0.00001);
		assertEquals(2.04814707396820, childOf3Node2.getSelectionValue(), 0.00001);
		
	}

}
