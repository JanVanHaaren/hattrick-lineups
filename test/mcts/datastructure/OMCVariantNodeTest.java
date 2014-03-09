package mcts.datastructure;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

public class OMCVariantNodeTest {
	
	private OMCVariantNode parentNode;
	private OMCVariantNode childNode1;
	private OMCVariantNode childNode2;
	private OMCVariantNode childNode3;
	private OMCVariantNode childOf3Node1;
	private OMCVariantNode childOf3Node2;

	@Before
	public void setUp() throws Exception {
		ChoiceSet choiceSetMock = mock(ChoiceSet.class);
		
		parentNode = new OMCVariantNodeInstantiation(null, choiceSetMock,0);
		childNode1 = new OMCVariantNodeInstantiation(parentNode, choiceSetMock, 0.5);
		childNode2 = new OMCVariantNodeInstantiation(parentNode, choiceSetMock, 2);
		childNode3 = new OMCVariantNodeInstantiation(parentNode, choiceSetMock, 4);
		childOf3Node1 = new OMCVariantNodeInstantiation(childNode3, choiceSetMock, 0);
		childOf3Node2 = new OMCVariantNodeInstantiation(childNode3, choiceSetMock, 1);
	}

	@Test
	public void testGetSelectionValue() {
		childNode1.backPropagate(1);
		childNode2.backPropagate(1);
		childNode2.backPropagate(1);
		childNode3.backPropagate(1);
		childOf3Node1.backPropagate(1);
		assertEquals(0.38461538461538461538461538461538, childNode1.getSelectionValue(), 0.00001);
		assertEquals(0.76923076923076923076923076923077, childNode2.getSelectionValue(), 0.00001);
		assertEquals(1.5384615384615384615384615384615, childNode3.getSelectionValue(), 0.00001);
		assertEquals(0, childOf3Node1.getSelectionValue(), 0.00001);
	}
	
	@Test
	public void testBackPropagate() {
		childNode1.backPropagate(-1);
		childNode2.backPropagate(1);
		childNode2.backPropagate(5);
		childNode3.backPropagate(0.5);
		childOf3Node1.backPropagate(0);
		
		assertEquals(2.0591260281974000631594738928395, parentNode.getStandardDeviation(), 0.00001);
		assertEquals(0, childNode1.getStandardDeviation(), 0.00001);
		assertEquals(2, childNode2.getStandardDeviation(), 0.00001);
		assertEquals(0.25, childNode3.getStandardDeviation(), 0.00001);
		assertEquals(0, childOf3Node1.getStandardDeviation(), 0.00001);
	}
	
	private class OMCVariantNodeInstantiation extends OMCVariantNode
	{
		private double urgency;

		public OMCVariantNodeInstantiation(MCTSNode parent, ChoiceSet choiceSet, double urgency) {
			super(parent, choiceSet);
			this.urgency = urgency;
		}

		@Override
		protected double getUrgency() {
			return urgency;
		}

		@Override
		protected MCTSNode generateChild(ChoiceSet choiceSet) {
			return new OMCVariantNodeInstantiation(this, choiceSet, 0);
		}
		
	}

}
