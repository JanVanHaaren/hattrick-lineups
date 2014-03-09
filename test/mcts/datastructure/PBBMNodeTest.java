package mcts.datastructure;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

public class PBBMNodeTest {

	private PBBMNode parentNode;
	private PBBMNode childNode1;
	private PBBMNode childNode2;
	private PBBMNode childNode3;
	private PBBMNode childOf3Node1;
	private PBBMNode childOf3Node2;
	
	@Before
	public void setUp() throws Exception {
		ChoiceSet choiceSetMock = mock(ChoiceSet.class);
		
		parentNode = new PBBMNode(null, choiceSetMock);
		childNode1 = new PBBMNode(parentNode, choiceSetMock);
		childNode2 = new PBBMNode(parentNode, choiceSetMock);
		childNode3 = new PBBMNode(parentNode, choiceSetMock);
		childOf3Node1 = new PBBMNode(childNode3, choiceSetMock);
		childOf3Node2 = new PBBMNode(childNode3, choiceSetMock);
	}

	@Test
	public void testGetUrgency() {
		childNode1.backPropagate(-1);
		childNode2.backPropagate(1);
		childNode2.backPropagate(5);
		childNode3.backPropagate(0.5);
		childOf3Node1.backPropagate(0);
		childOf3Node2.backPropagate(1);
		
		assertEquals(1, childNode1.getUrgency(), 0.00001);
		assertEquals(1, childNode2.getUrgency(), 0.00001);
		assertEquals(1, childNode3.getUrgency(), 0.00001);
		assertEquals(1, childOf3Node1.getUrgency(), 0.00001);
		
		//TODO: wat doen met standaardafwijkingen =0
		
//		assertEquals(2.0591260281974000631594738928395, parentNode.getStandardDeviation(), 0.00001);
//		assertEquals(0, childNode1.getStandardDeviation(), 0.00001);
//		assertEquals(2, childNode2.getStandardDeviation(), 0.00001);
//		assertEquals(0.25, childNode3.getStandardDeviation(), 0.00001);
//		assertEquals(0, childOf3Node1.getStandardDeviation(), 0.00001);
	}

}
