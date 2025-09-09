package crowdfundingapp;

import java.util.Date;

public class FlexibleCampaign extends Campaign {
    public FlexibleCampaign(int campaignId, String title, double goalAmount, Date deadline) {
        super(campaignId, title, goalAmount, deadline);
    }

    @Override
    public void settle() {
        if (getTotal() >= getGoalAmount()) {
            super.settle();
        } else {
            System.out.println("Flexible campaign '" + getTitle() + "' partially funded: " + getTotal());
            try {
                java.lang.reflect.Field statusField = Campaign.class.getDeclaredField("status");
                statusField.setAccessible(true);
                statusField.set(this, "PARTIALLY FUNDED");
            } catch (Exception e) { e.printStackTrace(); }
        }
    }
}
