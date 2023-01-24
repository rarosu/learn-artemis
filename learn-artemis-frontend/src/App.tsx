import axios, { AxiosError, AxiosResponse } from "axios";
import { FormEvent, useState } from "react";
import { Submission, SubmissionResponse } from "./submission.types";

const App = () => {
    const [messageName, setMessageName] = useState<string>("Message 1");
    const [value, setValue] = useState<string>("Some value for the message");
    const [isSubmitting, setIsSubmitting] = useState<boolean>();
    const [messageId, setMessageId] = useState<string | undefined>();
    const [axiosError, setAxiosError] = useState<AxiosError<SubmissionResponse, Submission> | undefined>();

    const handleSubmit = async (e: FormEvent<HTMLFormElement>) => {
        e.preventDefault();

        if (isSubmitting) {
            return;
        }

        console.log("Submitting!");

        try {
            setMessageId(undefined);
            setIsSubmitting(true);
            const response = await axios.post<SubmissionResponse, AxiosResponse<SubmissionResponse>, Submission>("http://localhost:8080", {messageName, value});
            setMessageId(response.data.messageId);
        } catch (error: any) {
            console.error(error);
            if (axios.isAxiosError(error)) {
                const axiosError = error as AxiosError<SubmissionResponse, Submission>;
                setAxiosError(axiosError);
            } else {
                throw error;
            }
        } finally {
            setIsSubmitting(false);
        }
    };

    return (
        <div className="container">
            <h1>Learn Artemis</h1>
            <p className="lead">This is an example form that will send a message to the backend-for-frontend (BFF) which will then use Artemis to communicate this message to a listener in the BFF which will log it.</p>
            <p className="lead">Just enter some information below and press submit.</p>
            <form onSubmit={async (e) => await handleSubmit(e)}>
                <div className="mb-3">
                    <label id="message-name-label" htmlFor="message-name" className="form-label">Message Name</label>
                    <input type="text" id="message-name" name="message-name" className="form-control" value={messageName} onChange={e => setMessageName(e.target.value)} />
                </div>
                <div className="mb-3">
                    <label id="name-value" htmlFor="value" className="form-label">Value</label>
                    <input type="text" id="value" name="value" className="form-control" value={value} onChange={e => setValue(e.target.value)} />
                </div>
                <div className="mb-3">
                    <button type="submit" id="submit" className="btn btn-primary btn-lg">
                        {!isSubmitting ? "Submit" : "Loading..." }
                    </button>
                </div>
                {isSubmitting && (
                    <div className="mb-3 d-flex justify-content-start align-items-center">
                        <div className="spinner-border" role="status" aria-hidden="true"></div>
                        <strong className="ms-3">Loading...</strong>
                    </div>
                )}
                
            </form>

            {messageId !== undefined && (
                <div className="alert alert-success" role="alert">
                    <h2>Message successfully submitted</h2>
                    <p><strong>Message ID: </strong> {messageId}</p>
                </div>
            )}

            {axiosError !== undefined && (
                <div className="alert alert-danger" role="alert">
                    <h2>Failed to submit</h2>
                    <p>Something went wrong when trying to submit.</p>
                    {axiosError.code !== undefined && (
                        <p><strong>Code:</strong> {axiosError.code}</p>
                    )}
                    {axiosError.response?.status !== 0 && (
                        <p><strong>Status code:</strong> {axiosError.response?.status} {axiosError.response?.statusText}</p>
                    )}
                    {axiosError.response?.data !== undefined && (
                        <p><strong>Response:</strong> {JSON.stringify(axiosError.response.data)}</p>
                    )}
                </div>
            )}
        </div>
    );
};

export default App;