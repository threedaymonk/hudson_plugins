package hudson.plugins.emailext;

import hudson.Plugin;
import hudson.plugins.emailext.plugins.ContentBuilder;
import hudson.plugins.emailext.plugins.EmailContent;
import hudson.plugins.emailext.plugins.EmailTriggerDescriptor;
import hudson.plugins.emailext.plugins.content.BuildLogContent;
import hudson.plugins.emailext.plugins.content.BuildNumberContent;
import hudson.plugins.emailext.plugins.content.BuildStatusContent;
import hudson.plugins.emailext.plugins.content.BuildURLContent;
import hudson.plugins.emailext.plugins.content.ChangesSinceLastBuildContent;
import hudson.plugins.emailext.plugins.content.ChangesSinceLastSuccessfulBuildContent;
import hudson.plugins.emailext.plugins.content.EnvContent;
import hudson.plugins.emailext.plugins.content.FailedTestsContent;
import hudson.plugins.emailext.plugins.content.HudsonURLContent;
import hudson.plugins.emailext.plugins.content.ProjectNameContent;
import hudson.plugins.emailext.plugins.content.ProjectURLContent;
import hudson.plugins.emailext.plugins.content.SVNRevisionContent;
import hudson.plugins.emailext.plugins.trigger.FailureTrigger;
import hudson.plugins.emailext.plugins.trigger.FixedTrigger;
import hudson.plugins.emailext.plugins.trigger.StillFailingTrigger;
import hudson.plugins.emailext.plugins.trigger.StillUnstableTrigger;
import hudson.plugins.emailext.plugins.trigger.SuccessTrigger;
import hudson.plugins.emailext.plugins.trigger.UnstableTrigger;

/**
 * Entry point of a plugin.
 *
 * <p>
 * There must be one {@link Plugin} class in each plugin.
 * See javadoc of {@link Plugin} for more about what can be done on this class.
 *
 * @author kyle.sweeney@valtech.com
 * @plugin
 */
public class EmailExtensionPlugin extends Plugin {
	
	@Override
	public void start() throws Exception {		
		//We are adding different Content plugins to the list of content types.
		addEmailContentPlugin(new BuildLogContent());
		addEmailContentPlugin(new BuildNumberContent());
		addEmailContentPlugin(new BuildStatusContent());
		addEmailContentPlugin(new BuildURLContent());
		addEmailContentPlugin(new ChangesSinceLastBuildContent());
		addEmailContentPlugin(new ChangesSinceLastSuccessfulBuildContent());
		addEmailContentPlugin(new EnvContent());
		addEmailContentPlugin(new FailedTestsContent());
		addEmailContentPlugin(new HudsonURLContent());
		addEmailContentPlugin(new ProjectNameContent());
		addEmailContentPlugin(new ProjectURLContent());
		addEmailContentPlugin(new SVNRevisionContent());
		
		addEmailTriggerPlugin(FailureTrigger.DESCRIPTOR);
		addEmailTriggerPlugin(StillFailingTrigger.DESCRIPTOR);
		addEmailTriggerPlugin(UnstableTrigger.DESCRIPTOR);
		addEmailTriggerPlugin(StillUnstableTrigger.DESCRIPTOR);
		addEmailTriggerPlugin(SuccessTrigger.DESCRIPTOR);
		addEmailTriggerPlugin(FixedTrigger.DESCRIPTOR);
	}
	
	private void addEmailContentPlugin(EmailContent content) {
		try {
			ContentBuilder.addEmailContentType(content);
		} catch (EmailExtException e) {
			System.err.println(e.getMessage());
		}
	}
	
	private void addEmailTriggerPlugin(EmailTriggerDescriptor trigger) {
		try {
			ExtendedEmailPublisher.addEmailTriggerType(trigger);
		} catch (EmailExtException e) {
			System.err.println(e.getMessage());
		}
	}
	
}
