package org.openmrs.module.kenyaemrml.web.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.kenyaemrml.api.ModelService;
import org.openmrs.module.kenyaemrml.domain.ModelInputFields;
import org.openmrs.module.kenyaemrml.domain.ScoringResult;
import org.openmrs.module.kenyaemrml.util.Util;
import org.openmrs.module.webservices.rest.SimpleObject;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.v1_0.controller.BaseRestController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * The main controller.
 */
@Controller
@RequestMapping(value = "/rest/" + RestConstants.VERSION_1 + "/ml")
public class MLRestController extends BaseRestController {
	
	protected final Log log = LogFactory.getLog(getClass());
	
	@RequestMapping(method = RequestMethod.POST, value = "/casefindingscore")
	@ResponseBody
	public Object receiveSHR(HttpServletRequest request) {
		ModelService modelService = new ModelService();
		String requestBody = null;
		try {
			requestBody = Util.fetchRequestBody(request.getReader());
			ModelInputFields inputFields = Util.extractVariablesFromRequestBody(requestBody);
			ScoringResult scoringResult = modelService.score("1", inputFields);
			System.out.println("Scoring result: " + scoringResult.toString());
			return scoringResult;
		}
		catch (IOException e) {
			return new SimpleObject().add("ServerResponse", "Error extracting request body");
		}
		
		//return new SimpleObject().add("Report", "The request could not be interpreted properly");
	}
}
