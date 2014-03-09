package mcts.datastructure;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

public class StandardUCTNodeTest {

	private StandardUCTNode parentNode;
	private StandardUCTNode childNode1;
	private StandardUCTNode childNode2;
	private StandardUCTNode childNode3;
	private StandardUCTNode childOf3Node1;
	private StandardUCTNode childOf3Node2;
	
	@Before
	public void setUp() throws Exception {
		ChoiceSet choiceSetMock = mock(ChoiceSet.class);
		
		parentNode = new StandardUCTNode(null, choiceSetMock,1);
		childNode1 = new StandardUCTNode(parentNode, choiceSetMock, 0.5);
		childNode2 = new StandardUCTNode(parentNode, choiceSetMock, 2);
		childNode3 = new StandardUCTNode(parentNode, choiceSetMock, 4);
		childOf3Node1 = new StandardUCTNode(childNode3, choiceSetMock, 0);
		childOf3Node2 = new StandardUCTNode(childNode3, choiceSetMock, 1);
	}

	@Test
	public void testGetSelectionValue() {
		//TODO: wat als visits 0 is
		
		//parent: 1.5, 6, 1
		//c1: 0, 1, 0.5
		//c2: 3, 2, 2
		//c3: 1, 2, 4
		//cc1: 0.5, 1, 0
		
		parentNode.backPropagate(1);
		childNode1.backPropagate(0);
		childNode2.backPropagate(1);
		childNode2.backPropagate(5);
		childNode3.backPropagate(1.5);
		childOf3Node1.backPropagate(0.5);
		
		
		assertEquals(0.66928309952292516423582109026491, childNode1.getSelectionValue(), 0.00001);
		assertEquals(4.8930184728248454157040536150018, childNode2.getSelectionValue(), 0.00001);
		assertEquals(4.7860369456496908314081072300035, childNode3.getSelectionValue(), 0.00001);
		assertEquals(0.5, childOf3Node1.getSelectionValue(), 0.00001);
	}

}
